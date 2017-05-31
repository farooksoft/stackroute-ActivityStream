import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { User, Circle, Message } from '../_models/index';
import { UserService, CircleService, MessageService, AlertService } from '../_services/index';
//import '../assets/home.css';

@Component({
    moduleId: module.id.toString(),
    selector:'home',
    templateUrl: 'home.component.html',
    
})

export class HomeComponent implements OnInit {
    currentUser: User;
    users: User[] = [];
    circles: Circle[] = [];
    messages: Message[] = [];
    newMessage: any = {};

    constructor(
        private router: Router,
        private userService: UserService, 
        private circleService: CircleService, 
        private messageService: MessageService,
        private alertService: AlertService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }

    ngOnInit() {
        this.loadAllUsers();
        this.loadAllCircles();
        this.loadAllMessages();
    }

    deleteUser(id: number) {
        this.userService.delete(id).subscribe(() => { this.loadAllUsers() });
    }

    private loadAllUsers() {
        this.userService.getAll().subscribe(users => { this.users = users._embedded.users; });
    }

    private loadAllCircles() {
        this.circleService.getAll().subscribe(circles => { this.circles = circles; });
    }

    private loadAllMessages() {
        this.messageService.getAll().subscribe(messages => { this.messages = messages._embedded.messages; });
    }

    private sendMessage() {
        this.newMessage.senderid = 1;
        this.newMessage.circleid = 1;
        this.newMessage.msgtype = 'text';
        this.newMessage.createddate = 1496048341709;

         this.messageService.create(this.newMessage)
            .subscribe(
                data => {
                    this.router.navigate(['/']);
                },
                error => {
                    //this.alertService.error(error);
                    this.router.navigate(['/']);
                });
    }
}