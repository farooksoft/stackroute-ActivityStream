import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';

import { Message } from '../_models/index';

@Injectable()
export class MessageService {
    constructor(private http: Http) { }

    getAll() {
        return this.http.get('http://localhost:8890/messages', this.jwt()).map((response: Response) => response.json());
    }

    getById(id: number) {
        return this.http.get('http://localhost:8890/message/' + id, this.jwt()).map((response: Response) => response.json());
    }

    create(message: Message) {
        return this.http.post('http://localhost:8890/messages/', message, this.jwt()).map((response: Response) => response.json());
    }

    update(message: Message) {
return this.http.put('http://localhost:8890/message/' + message.id, message, this.jwt()).map((response: Response) => response.json());
    }

    delete(id: number) {
        return this.http.delete('http://localhost:8890/message/' + id, this.jwt()).map((response: Response) => response.json());
    }

    getMessageByCircle(circleid: number) {
        return this.http.get('http://localhost:8890/message/circle' + circleid, this.jwt()).map((response: Response) => response.json());
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