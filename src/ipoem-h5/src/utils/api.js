import Axios from 'axios'
import config from '../../static/config/config'

const instance = Axios.create({
  withCredentials: true,
  timeout: 60 * 1000,
  baseURL: config.baseURL
})

export default instance
