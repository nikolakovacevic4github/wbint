export interface IUserExist {
  userExist: boolean;
  redirectUrl?: string;
}

export interface IRegisteredUser {
  userRegistered: boolean;
  redirectUrl?: string;
}

export class UserExist implements IUserExist {
  constructor(public userExist: boolean, public redirectUrl?: string) {}
}

export class RegisteredUser implements IRegisteredUser{
  constructor(public userRegistered: boolean,  public redirectUrl?: string) {
  }
}
