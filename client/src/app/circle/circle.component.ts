import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../_models/index';
import { UserService } from '../_services/index';
import { Circle } from '../_models/index';
import { CircleService, AlertService } from '../_services/index';

@Component( {
    moduleId: module.id.toString(),
    selector: 'circle',
    templateUrl: 'circle.component.html',
} )

export class CircleComponent implements OnInit {
    users: User[] = [];
    circles: Circle[] = [];
    newCircle: any = {};

    constructor( private router: Router, private userService: UserService, private circleService: CircleService, private alertService: AlertService ) {
        this.circles = JSON.parse( localStorage.getItem( 'circles' ) );
    }

    ngOnInit() {
        this.loadAllCircles();
        this.loadAllUsers();
    }
   
    private loadAllUsers() {
        this.userService.getAll().subscribe( users => { this.users = users._embedded.users; } );
    }

    private loadAllCircles() {
        this.circleService.getAll().subscribe( circles => { this.circles = circles; } );
    }

    private addCircle() {
        
        this.newCircle.status = 'created';
        this.newCircle.createddate = new Date().getTime();

        this.circleService.create(this.newCircle)
            .subscribe(
                data => {
                    this.router.navigate(['/']);
                },
                error => {
                    this.alertService.error(error);
                });
    }

}