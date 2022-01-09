import {Component, OnInit, RendererFactory2, Renderer2, OnDestroy, Inject} from '@angular/core';
import {Title} from '@angular/platform-browser';
import {Router, ActivatedRouteSnapshot, NavigationEnd} from '@angular/router';
import {TranslateService, LangChangeEvent} from '@ngx-translate/core';
import * as dayjs from 'dayjs';

import {AccountService} from 'app/core/auth/account.service';
import {
  AuthenticationResult,
  InteractionStatus,
  PopupRequest,
  RedirectRequest
} from "@azure/msal-browser";
import {filter, takeUntil} from "rxjs/operators";
import {MSAL_GUARD_CONFIG, MsalBroadcastService, MsalGuardConfiguration, MsalService} from "@azure/msal-angular";
import {Subject} from "rxjs";

@Component({
  selector: 'jhi-main',
  templateUrl: './main.component.html',
})
export class MainComponent implements OnInit, OnDestroy {
  isIframe = false;
  loginDisplay = false;
  private readonly _destroying$ = new Subject<void>();
  private renderer: Renderer2;


  constructor(
    private accountService: AccountService,
    private titleService: Title,
    private router: Router,
    private translateService: TranslateService,
    rootRenderer: RendererFactory2
  ) {
    this.renderer = rootRenderer.createRenderer(document.querySelector('html'), null);
  }

  ngOnInit(): void {

    // try to log in automatically
    this.accountService.identity().subscribe();

    this.router.events.subscribe(event => {
      if (event instanceof NavigationEnd) {
        this.updateTitle();
      }
    });

    this.translateService.onLangChange.subscribe((langChangeEvent: LangChangeEvent) => {
      this.updateTitle();
      dayjs.locale(langChangeEvent.lang);
      this.renderer.setAttribute(document.querySelector('html'), 'lang', langChangeEvent.lang);
    });
  }

  ngOnDestroy(): void {
    this._destroying$.next(undefined);
    this._destroying$.complete();
  }

  private getPageTitle(routeSnapshot: ActivatedRouteSnapshot): string {
    const title: string = routeSnapshot.data['pageTitle'] ?? '';
    if (routeSnapshot.firstChild) {
      return this.getPageTitle(routeSnapshot.firstChild) || title;
    }
    return title;
  }

  private updateTitle(): void {
    let pageTitle = this.getPageTitle(this.router.routerState.snapshot.root);
    if (!pageTitle) {
      pageTitle = 'global.title';
    }
    this.translateService.get(pageTitle).subscribe(title => this.titleService.setTitle(title));
  }
}
