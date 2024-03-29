import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {HttpClientModule} from '@angular/common/http';
import {FormsModule} from '@angular/forms';

import {AppRoutingModule} from './app-routing.module';

import {AppComponent} from './app.component';
import {UporabnikiComponent} from './uporabnik/uporabniki.component';
import {UporabnikiDodajComponent} from './uporabnik/uporabniki-dodaj.component';
import {UporabnikPodrobnostiComponent} from './uporabnik/uporabnik-podrobnosti.component';
import {UporabnikService} from './uporabnik/services/uporabnik.service';
import {ShoppingListService} from './uporabnik/services/shopping-list.service';
import { ShoppingListDetailsComponent } from './uporabnik/shopping-list-details/shopping-list-details.component';


@NgModule({
    imports: [
        BrowserModule,
        HttpClientModule,
        AppRoutingModule,
        FormsModule
    ],
    declarations: [
        AppComponent,
        UporabnikiComponent,
        UporabnikPodrobnostiComponent,
        UporabnikiDodajComponent,
        ShoppingListDetailsComponent
    ],
    providers: [UporabnikService, ShoppingListService],
    bootstrap: [AppComponent]
})
export class AppModule {
}

