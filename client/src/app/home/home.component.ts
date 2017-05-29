import { Component, OnInit } from '@angular/core';

import { User } from '../_models/index';
import { UserService } from '../_services/index';
import { Circle } from '../_models/index';
import { CircleService } from '../_services/index';
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

    constructor(private userService: UserService, private circleService: CircleService) {
        this.currentUser = JSON.parse(localStorage.getItem('currentUser'));
    }

    ngOnInit() {
        this.loadAllUsers();
        this.loadAllCircles();
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
}