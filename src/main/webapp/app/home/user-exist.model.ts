export interface IUserExist {
  userExist: boolean;
  redirectUrl?: string;
}

export class UserExist implements IUserExist {
  constructor(public userExist: boolean, public redirectUrl?: string) {}
}
