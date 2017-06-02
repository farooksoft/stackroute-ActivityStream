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
    currentCircle: Circle;
    users: User[] = [];
    circles: Circle[] = [];
    mycircles: Circle[] = [];
    messages: Message[] = [];
    newMessage: any = {};

    constructor(
        private router: Router,
        private userService: UserService, 
        private circleService: CircleService, 
        private messageService: MessageService,
        private alertService: AlertService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
        this.currentCircle = new Circle;
    }

    ngOnInit() {
        this.loadAllUsers();
        this.loadAllCircles();
        this.loadMyCircles();
    }

    deleteUser(id: number) {
        this.userService.delete(id).subscribe(() => { this.loadAllUsers() });
    }

    private loadAllUsers() {
        this.userService.getAll().subscribe(users => { this.users = users._embedded.users; });
    }

    private loadAllCircles() {

        this.circleService.getAll().subscribe(circles => { 
            this.circles = circles._embedded.circles; 
            if (this.currentCircle == null) { 
                this.currentCircle = this.circles[0]; 
                this.loadMessagesByCircle(this.currentCircle);
            } 
        });
    }

    private loadMyCircles() {

        this.circleService.getcirclesbyuser(this.currentUser).subscribe(usercircles => { 
            let globalcircles = this.circles; 
            if(this.circles.length > 0) {
                for (let i=0; i < usercircles.length; i++) {
                    for(let j=0; j < globalcircles.length; j++) {
                        if(usercircles[i].circleid = globalcircles[j].id) {
                            this.mycircles.push(globalcircles[j]);
                            break;
                        }
                    }
                }
            }
            else {
                for (let i=0; i < usercircles.length; i++) {
                    this.circleService.getById(usercircles[i].circleId).subscribe(circle => { 
                        this.mycircles.push(circle);
                    });
                }
            }
        });
    }
    
    private loadAllMessages() {
        this.messageService.getAll().subscribe(messages => { this.messages = messages._embedded.messages; });
    }

    private loadMessagesByCircle(circle: Circle) {

        this.currentCircle = circle;
        this.messageService.getMessageByCircle(circle.id).subscribe(messages => { this.messages = messages; });
    }

    private sendMessage() {
        this.newMessage.senderid = this.currentUser.id;
        this.newMessage.circleid = this.currentCircle.id;
        this.newMessage.msgtype = 'text';
        this.newMessage.createddate = new Date().getTime();

         this.messageService.create(this.newMessage)
            .subscribe(
                data => {
                    this.router.navigate(['/home']);
                },
                error => {
                    this.alertService.error(error);
                });
    }

    private getUsername(userid: number):any {
        for (let user of this.users) {
            if(userid == user.id) {
                return user.username;
            }
        }
    }
}