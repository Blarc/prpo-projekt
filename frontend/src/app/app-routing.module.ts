import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';

import {UporabnikiComponent} from './uporabnik/uporabniki.component';
import {UporabnikPodrobnostiComponent} from './uporabnik/uporabnik-podrobnosti.component';
import {UporabnikiDodajComponent} from './uporabnik/uporabniki-dodaj.component';
import {ShoppingListDetailsComponent} from './uporabnik/shopping-list-details/shopping-list-details.component';

const routes: Routes = [
    {path: '', redirectTo: '/uporabniki', pathMatch: 'full'},
    {path: 'uporabniki', component: UporabnikiComponent},
    {path: 'uporabniki/:id', component: UporabnikPodrobnostiComponent},
    {path: 'dodajuporabnika', component: UporabnikiDodajComponent},
    {path: 'shoppingLists/:id', component: ShoppingListDetailsComponent}
];

@NgModule({
    imports: [RouterModule.forRoot(routes)],
    exports: [RouterModule]
})
export class AppRoutingModule {
}
