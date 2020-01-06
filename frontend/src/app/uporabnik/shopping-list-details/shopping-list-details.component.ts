import { Component, OnInit } from '@angular/core';
import {ShoppingList} from '../models/shoppingList';
import {switchMap} from 'rxjs/operators';
import {ActivatedRoute, Params} from '@angular/router';
import {ShoppingListService} from '../services/shopping-list.service';

@Component({
  selector: 'app-shopping-list-details',
  templateUrl: './shopping-list-details.component.html',
  styleUrls: ['./shopping-list-details.component.css']
})
export class ShoppingListDetailsComponent implements OnInit {
  shoppingList: ShoppingList;

  constructor(private shoppingListService: ShoppingListService,
              private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.route.params.pipe(
        switchMap((params: Params) => this.shoppingListService.getShoppingList(params['id'])))
        .subscribe(shoppingList => this.shoppingList = shoppingList);
  }

}
