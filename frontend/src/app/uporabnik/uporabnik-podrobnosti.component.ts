import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Params, Router} from '@angular/router';
import {Location} from '@angular/common';

import { switchMap } from 'rxjs/operators';

import {Uporabnik} from './models/uporabnik';
import {UporabnikService} from './services/uporabnik.service';
import {ShoppingList} from './models/shoppingList';
import {ShoppingListService} from './services/shopping-list.service';
import {ShoppingListDto} from './models/shoppingListDto';

@Component({
    moduleId: module.id,
    selector: 'uporabnik-podrobnosti',
    templateUrl: 'uporabnik-podrobnosti.component.html'
})
export class UporabnikPodrobnostiComponent implements OnInit {
    uporabnik: Uporabnik;
    shoppingListDto: ShoppingListDto = new ShoppingListDto();

    constructor(
        private shoppingListService: ShoppingListService,
        private uporabnikService: UporabnikService,
        private route: ActivatedRoute,
        private location: Location,
        private router: Router) {
    }

    ngOnInit(): void {
       this.route.params.pipe(
            switchMap((params: Params) => this.uporabnikService.getUporabnik(+params['id'])))
            .subscribe(uporabnik => this.uporabnik = uporabnik);
    }

    nazaj(): void {
        this.location.back();
    }

    submitForm() {
        this.shoppingListDto.userId = this.uporabnik.id;
        this.shoppingListService
            .create(this.shoppingListDto)
            .subscribe(shoppingList => this.uporabnik.shoppingList.push(shoppingList));
        this.shoppingListDto.userId = null;
        this.shoppingListDto.name = null;
        this.shoppingListDto.description = null;
    }

    delete(shoppingList: ShoppingList): void {
        this.shoppingListService
            .delete(shoppingList.id)
            .subscribe(shoppingListId => this.uporabnik.shoppingList = this.uporabnik.shoppingList.filter(u => u.id !== shoppingListId));
    }


    details(shoppingList: ShoppingList) {
        this.router.navigate(['/shoppingLists', shoppingList.id]);
    }
}
