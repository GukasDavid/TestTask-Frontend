import axios from 'axios';
import authHeader from './auth-header';

const API_URL = 'http://localhost:8080/api/game/';

class UserService {
  getPublicContent() {
    return axios.get(API_URL + 'statistic');
  }

  getNewDigit() {
    return axios.get(API_URL + 'generateDigit', { headers: authHeader() });
  }

  equalsResult(digit){
    return axios
      .get(API_URL + 'equals/'+digit,{ headers: authHeader() });
  }
}

export default new UserService();
