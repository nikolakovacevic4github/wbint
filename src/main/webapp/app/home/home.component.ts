import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil} from 'rxjs/operators';

import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';
import { MsalService } from '@azure/msal-angular';
import {ProfileService} from "../layouts/profiles/profile.service";
import {WBService} from "./wb.service";

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  public redirectMessage: boolean;
  public user_name: string | undefined;
  public msalIncluded = false;
  private readonly destroy$ = new Subject<void>();
  private email: string | undefined;

  constructor(
    private accountService: AccountService,
    private profileService: ProfileService,
    private router: Router,
    private authService: MsalService,
    private wbService: WBService) {
    this.email = 'ddp.test1@oneun.org';
    this.redirectMessage = false;
  }

  ngOnInit(): void {
    this.profileService.getProfileInfo().subscribe(profileInfo => {
      if(profileInfo.inProduction) {
          this.authService.loginRedirect().subscribe(value => {
            console.log(value);
            const msalAccountInfo = this.authService.instance.getActiveAccount();
            console.log(msalAccountInfo);
            this.email = msalAccountInfo?.username;
            this.user_name = msalAccountInfo?.name;
            this.msalIncluded = true;
            this.wbService.checkIfUserExist(this.email).subscribe(
              usrResp => {
                if(usrResp.body?.userExist) {
                  this.redirectToExternalLink(usrResp.body.redirectUrl);
                }
              }
            );
          });
      }
    });

    if (!this.msalIncluded) {
      this.wbService.checkIfUserExist(this.email).subscribe(
        value => {
          if(value.body?.userExist) {
            this.redirectToExternalLink(value.body.redirectUrl);
          }
        }
      );
    }

    this.accountService
      .getAuthenticationState()
      .pipe(takeUntil(this.destroy$))
      .subscribe(account => (this.account = account));
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }

  private redirectToExternalLink(redirectUrl: string | undefined): void {
    if(!redirectUrl) {
      return;
    }
    this.redirectMessage = true;
    setInterval(() => {
      this.redirectMessage = false;
      window.location.href = redirectUrl;
      console.log("redirect...");
    }, 5000);
  }
}
