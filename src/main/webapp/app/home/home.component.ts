import {Component, OnInit, OnDestroy} from '@angular/core';
import {Router} from '@angular/router';
import {Observable, Subject} from 'rxjs';

import {AccountService} from 'app/core/auth/account.service';
import {Account} from 'app/core/auth/account.model';
import {ProfileService} from "../layouts/profiles/profile.service";
import {WBService} from "./wb.service";
import jwtDecode, {JwtPayload} from "jwt-decode";
import {AuthServerProvider} from "../core/auth/auth-jwt.service";

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  profile!: any;
  account: Account | null = null;
  loader = true;
  userNotExist = false;
  userNotRegistered = false;
  tryingToRegister = false;
  userRegistered = false;
  userRegisteredSuccessfully=false;

  private readonly destroy$ = new Subject<void>();

  constructor(
    private accountService: AccountService,
    private profileService: ProfileService,
    private authService: AuthServerProvider,
    private router: Router,
    private wbService: WBService) {
  }

  ngOnInit(): void {
    this.profileService.getProfileInfo().subscribe(profileInfo => {
      if (profileInfo.inProduction) {
        console.log("PRODUCTION>>>");
        this.getProfile().subscribe(profile => {
          this.profile = jwtDecode<JwtPayload>(profile[0].id_token);
          console.log(profile);
          this.checifUserExist(this.profile.email);
        });
      } else {
        console.log("DEVELOPMENT>>>");
        this.profile = {
          email: "ddp.test1@oneun.org",
          name: "Test1",
          surr_name: "test1"
        }
        this.checifUserExist(this.profile.email);
      }
    });

    // this.accountService
    //   .getAuthenticationState()
    //   .pipe(takeUntil(this.destroy$))
    //   .subscribe(account => (this.account = account));
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  getProfile(): Observable<any> {
    return this.authService.getUserInfoFromAD();
  }

  registerUserToWB(): void {
    this.tryingToRegister = true;

    const name_array = this.profile.name.trim().split(/\s+/);
    let first_name = name_array.slice(0, Math.floor(name_array.length / 2));
    let last_name = name_array.slice(Math.floor(name_array.length / 2), name_array.length)

    first_name = first_name.reduce((previousValue: string, currentValue: string) => previousValue + " " + currentValue).trim();
    last_name = last_name.reduce((previousValue: string, currentValue: string) => previousValue + " " + currentValue).trim();

    this.wbService.registerUser( this.profile.email, first_name, last_name).subscribe(
      value => {
        if (value.body?.userRegistered) {            
          this.profile.message="You are successfuly registered at Development Data Partnership Portal";
          this.redirectToExternalLinkAfterSeconds(value.body.redirectUrl, 5000);
        } else {
          this.loader = false;
          this.tryingToRegister = false;
          this.userNotExist = false;
          this.userNotRegistered = true;
          this.userRegisteredSuccessfully=false;
        }
      }
    );
  }

  private redirectToExternalLink(redirectUrl: string | undefined): void {
    if (!redirectUrl) {
      return;
    }
    window.location.href = redirectUrl;
  }

  private checifUserExist(email: string): void {
    this.loader = true;
    this.userNotExist = false;
    this.userNotRegistered = false;
    setTimeout(() => {
      this.wbService.checkIfUserExist(email).subscribe(
        value => {
          if (value.body?.userExist) {
            this.profile.message="You are already registered";
            this.redirectToExternalLinkAfterSeconds(value.body.redirectUrl, 5000);
          } else {
            this.userNotExist = true;
            this.loader = false;
            this.userNotRegistered = false;
            this.userRegisteredSuccessfully=false;
          }
        });
    }, 5000);
  }

  private redirectToExternalLinkAfterSeconds( redirectURL: string | undefined, miliseconds: number): void {
    this.userRegistered = true;
    this.userNotExist = false;
    this.userNotRegistered = false;
    this.loader = false;     
    this.tryingToRegister = false;
    console.log("Before Redirect>>>");
    setTimeout(() => {
            this.redirectToExternalLink(redirectURL);
    }, miliseconds)
    console.log("After Redirect>>>");
    this.userRegistered = true;
    this.userNotExist = false;
    this.userNotRegistered = false;
    this.loader = false;     
    this.tryingToRegister = false;
    console.log("After Redirect2>>>");
   // console.log(!this.loader && this.userNotExist  && !this.userNotRegistered);
  }


}
