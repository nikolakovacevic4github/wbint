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
          email: "ddp.test4@oneun.org",
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
    this.wbService.registerUser( this.profile.email, this.profile.name, this.profile.surr_name).subscribe(
      value => {
        if (value.body?.userRegistered) {
          this.redirectToExternalLink(value.body.redirectUrl);
        } else {
          this.loader = false;
          this.tryingToRegister = false;
          this.userNotExist = false;
          this.userNotRegistered = true;
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
    setInterval(() => {
      this.wbService.checkIfUserExist(email).subscribe(
        value => {
          if (value.body?.userExist) {
            this.redirectToExternalLink(value.body.redirectUrl);
          } else {
            this.userNotExist = true;
            this.loader = false;
            this.userNotRegistered = false;
          }
        });
    }, 5000);
  }
}
