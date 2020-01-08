import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {ShoppingList} from '../models/shoppingList';
import {Observable} from 'rxjs';
import {catchError} from 'rxjs/operators';
import {ShoppingListDto} from '../models/shoppingListDto';
import {Uporabnik} from '../models/uporabnik';

@Injectable({
  providedIn: 'root'
})
export class ShoppingListService {

  private headers = new HttpHeaders({'Content-Type': 'application/json'});
  private url = 'http://localhost:8080/v1/shoppingLists';


  constructor(private http: HttpClient) { }

  getShoppingLists(): Observable<ShoppingList[]> {
    return this.http.get<ShoppingList[]>(this.url)
        .pipe(catchError(this.handleError))
  }

  getShoppingList(id: number): Observable<ShoppingList> {
    const url = `${this.url}/${id}`;
    return this.http.get<ShoppingList>(url)
        .pipe(catchError(this.handleError));
  }

  delete(id: number): Observable<number> {
    const url = `${this.url}/${id}`;
    return this.http.delete<number>(url, {headers: this.headers})
        .pipe(catchError(this.handleError));
  }

  create(shoppingList: ShoppingListDto): Observable<ShoppingList> {
    return this.http.post<ShoppingList>(this.url, JSON.stringify(shoppingList), {headers: this.headers})
        .pipe(catchError(this.handleError));
  }

  private handleError(error: any): Promise<any> {
    console.error('Prišlo je do napake', error);
    return Promise.reject(error.message || error);
  }
}
