import { Component, OnInit, OnDestroy } from '@angular/core';
import { Router } from '@angular/router';
import { Subject } from 'rxjs';
import { takeUntil } from 'rxjs/operators';

import { AccountService } from 'app/core/auth/account.service';
import { Account } from 'app/core/auth/account.model';
import { MsalService } from '@azure/msal-angular';
import {ProfileService} from "../layouts/profiles/profile.service";

@Component({
  selector: 'jhi-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss'],
})
export class HomeComponent implements OnInit, OnDestroy {
  account: Account | null = null;
  public user_name: string | undefined;
  private readonly destroy$ = new Subject<void>();

  constructor(private accountService: AccountService, private profileService: ProfileService, private router: Router, private authService: MsalService) {}

  ngOnInit(): void {
    this.profileService.getProfileInfo().subscribe(profileInfo => {
      if(profileInfo.inProduction) {
          this.authService.loginRedirect().subscribe(value => {
            console.log(value);
          });
      }
    });
    this.accountService
      .getAuthenticationState()
      .pipe(takeUntil(this.destroy$))
      .subscribe(account => (this.account = account));

    //TODO: user_name hardoced -> need to fetch from UNDP
    this.user_name = 'User Name';
  }

  login(): void {
    this.router.navigate(['/login']);
  }

  ngOnDestroy(): void {
    this.destroy$.next();
    this.destroy$.complete();
  }
}
