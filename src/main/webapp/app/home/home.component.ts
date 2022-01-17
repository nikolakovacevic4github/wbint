import {Component, OnInit, OnDestroy} from '@angular/core';
import {Router} from '@angular/router';
import {Subject} from 'rxjs';

import {AccountService} from 'app/core/auth/account.service';
import {Account} from 'app/core/auth/account.model';
import {MsalBroadcastService, MsalService} from '@azure/msal-angular';
import {ProfileService} from "../layouts/profiles/profile.service";
import {WBService} from "./wb.service";
import {filter} from "rxjs/operators";
import {AuthenticationResult, EventMessage, EventType, InteractionStatus} from "@azure/msal-browser";
import {HttpClient} from "@angular/common/http";

const GRAPH_ENDPOINT = 'https://graph.microsoft-ppe.com/v1.0/me';

type ProfileType = {
  givenName?: string,
  surname?: string,
  userPrincipalName?: string,
  id?: string
};

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  profile!: ProfileType;
  account: Account | null = null;
  loginDisplay = false;
  public redirectMessage: boolean;
  public user_name: string | undefined;
  private readonly destroy$ = new Subject<void>();
  private email: string | undefined;

  constructor(
    private accountService: AccountService,
    private profileService: ProfileService,
    private router: Router,
    private wbService: WBService,
    private http: HttpClient) {
    this.email = 'ddp.test1@oneun.org';
    this.redirectMessage = false;
  }

  ngOnInit(): void {
    this.profileService.getProfileInfo().subscribe(profileInfo => {
      if (profileInfo.inProduction) {
        console.log("PRODUCTION>>>");
        this.getProfile();
      }
    });


    // this.wbService.checkIfUserExist(this.email).subscribe(
    //   value => {
    //     if (value.body?.userExist) {
    //       this.redirectToExternalLink(value.body.redirectUrl);
    //     }
    //   });

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

  getProfile(): void {
    // this.http.get(GRAPH_ENDPOINT)
    //   .subscribe(profile => {
    //     this.profile = profile;
    //   });
  }

  private redirectToExternalLink(redirectUrl: string | undefined): void {
    if (!redirectUrl) {
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
