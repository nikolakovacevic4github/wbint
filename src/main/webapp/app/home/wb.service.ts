import {Injectable} from "@angular/core";
import {EMPTY, Observable, of} from "rxjs";
import {IRegisteredUser, IUserExist, UserExist} from "./user-exist.model";
import {HttpClient, HttpParams, HttpResponse} from "@angular/common/http";
import {ApplicationConfigService} from "../core/config/application-config.service";

@Injectable({ providedIn: 'root' })
export class WBService {

  constructor(private http: HttpClient, private applicationConfigService: ApplicationConfigService) {}

  public checkIfUserExist(email: string | undefined): Observable<HttpResponse<IUserExist>> {
    if (!email) {
      return EMPTY;
    }
    // if (email === 'ddp.test1@oneun.org') {
    //   const mockResponse = new HttpResponse<IUserExist>({body: new UserExist(false, undefined)});
    //   return of(mockResponse);
    // }
    const options = new HttpParams().set("email", String(email));
    return this.http.get<IUserExist>(this.applicationConfigService.getEndpointFor('/api/wb/searchUser'), {observe: "response", params: options});
  }

  public registerUser(email: string, name: string, surrName: string): Observable<HttpResponse<IRegisteredUser>> {
    const options = new HttpParams().set("email", String(email)).set("name", name).set("surrName", surrName);
    return this.http.get<IRegisteredUser>(this.applicationConfigService.getEndpointFor('/api/wb/registerUser'), {observe: "response", params: options});
  }
}
