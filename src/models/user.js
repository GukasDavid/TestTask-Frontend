export default class User {
  constructor(username, email, password,attemptCount, successAttemptCount) {
    this.username = username;
    this.email = email;
    this.password = password;
  }
}
