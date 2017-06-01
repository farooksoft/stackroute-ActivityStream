import { Component, OnInit } from '@angular/core';

import { User } from '../_models/index';
import { UserService } from '../_services/index';
import { Circle } from '../_models/index';
import { CircleService } from '../_services/index';

@Component( {
    moduleId: module.id.toString(),
    selector: 'circle',
    templateUrl: 'circle.component.html',
} )

export class CircleComponent implements OnInit {
    ngOnInit(): void {
        throw new Error( 'Method not implemented.' );
    }

    users: User[] = [];
    circles: Circle[] = [];

    constructor( private userService: UserService, private circleService: CircleService ) {
        this.circles = JSON.parse( localStorage.getItem( 'circles' ) );
    }

    OnInit() {
        this.loadAllCircles();
    }

    private loadAllUsers() {
        this.userService.getAll().subscribe( users => { this.users = users._embedded.users; } );
    }

    private loadAllCircles() {
        this.circleService.getAll().subscribe( circles => { this.circles = circles; } );
    }

}