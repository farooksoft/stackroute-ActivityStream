import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { Circle, UserCircle, User } from '../_models/index';

@Injectable()
export class CircleService {
    constructor(private http: Http) { }

    getAll() {
        return this.http.get('http://localhost:8889/circles', this.jwt()).map((response: Response) => response.json());
    }

    getById(id: number) {
        return this.http.get('http://localhost:8889/circles/' + id, this.jwt()).map((response: Response) => response.json());
    }

    create(circle: Circle) {
        return this.http.post('http://localhost:8889/circles/', circle, this.jwt()).map((response: Response) => response.json());
    }

    update(circle: Circle) {
return this.http.put('http://localhost:8889/users/' + circle.id, circle, this.jwt()).map((response: Response) => response.json());
    }

    addusercircle(usercircle: UserCircle) {
        return this.http.post('http://localhost:8889/circles/usercircle?userid=' + usercircle.userid + '&circleid=' + usercircle.circleid, usercircle, this.jwt()).map((response: Response) => response.json());
    }

    delete(id: number) {
        return this.http.delete('http://localhost:8889/circles/' + id, this.jwt()).map((response: Response) => response.json());
    }

    getcirclesbyuser(currentuser: User) {
        return this.http.get('http://localhost:8889/circles/usercircles?userid=' + currentuser.id, this.jwt()).map((response: Response) => response.json());   
    }

    // private helper methods

    private jwt() {
        // create authorization header with jwt token
        let currentUser = JSON.parse(localStorage.getItem('currentUser'));
        if (currentUser && currentUser.token) {
            let headers = new Headers({ 'Authorization': 'Bearer ' + currentUser.token });
            return new RequestOptions({ headers: headers });
        }
        else {
            let headers = new Headers({ 'Content-Type': 'application/json' });
            return new RequestOptions({ headers: headers });
        }
    }
}