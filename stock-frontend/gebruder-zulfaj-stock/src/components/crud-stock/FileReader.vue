<template>
  <div>
    <input type="file" @change="handleFileUpload" />
    <div v-if="processedData && processedData.length">
      <h3>Processed Data:</h3>
      <pre>{{ processedData }}</pre>
    </div>
  </div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';

export default {
  setup() {
    const processedData = ref([]);

    const handleFileUpload = async (event) => {
      const file = event.target.files[0];
      if (file) {
        const reader = new FileReader();

        reader.onload = () => {
          const content = reader.result;
          if (typeof content === 'string') {
            const lines = content.split('\n');
            console.log('File content lines:', lines); // Log lines for debugging
            const extractedData = extractAndProcessData(lines);
            processedData.value = extractedData;
            console.log('Extracted Data:', extractedData); // Log extracted data for debugging
            if (extractedData.length > 0) {
              sendDataToBackend(extractedData);
            }
          }
        };

        reader.readAsText(file, 'UTF-8');
      }
    };

    const extractAndProcessData = (lines) => {
      const dataRegex = /(\d{6})\s+(\d+)\s+mm/;
      const targetData = lines.filter(line => dataRegex.test(line));

      const processed = targetData.map(line => {
        const match = line.match(dataRegex);
        console.log('Matched line:', match); // Log matched line for debugging
        if (match) {
          const code = match[1];
          const length = parseFloat(match[2].replace(/\s/g, '')); // Remove spaces and convert to number
          return { code, length };
        }
        return null;
      }).filter(item => item !== null);

      return processed;
    };

    const sendDataToBackend = async (data) => {
      try {
        console.log('Data to be sent to backend:', data); // Log data before sending
        await axios.post('http://localhost:8080/api/process-data', data);
        console.log('Data sent to backend successfully.');
      } catch (error) {
        console.error('Error sending data to backend:', error);
      }
    };

    return {
      handleFileUpload,
      processedData,
    };
  }
};
</script>

<style>
/* Add some basic styling */
ul {
  list-style-type: none;
  padding: 0;
}
li {
  margin: 5px 0;
}
</style>
