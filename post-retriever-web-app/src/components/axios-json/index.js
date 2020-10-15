import axios from 'axios'

const useFetch = async ({ url, config }) => {
  try {
    const response = await axios(url, config)
    if (response.status < 200 || response.status >= 300) throw response.data
    return [true, { json: response.data }]
  } catch (e) {
    return [false, { json: e }]
  }
}

export default useFetch
