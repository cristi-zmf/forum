import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';

import {AppComponent} from './app.component';
import {ArticleComponent} from './article/article.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {HttpApiPrefixInterceptor} from "./http-api-prefix.interceptor";
import {InfiniteScrollModule} from "ngx-infinite-scroll";
import {NgxSpinnerModule} from "ngx-spinner";
import {ArticleService} from "./article/article.service";
import { ArticleEditComponent } from './article/edit/article-edit/article-edit.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatDialogModule} from "@angular/material/dialog";
import {RouterModule} from "@angular/router";
import {FormsModule} from "@angular/forms";

@NgModule({
  declarations: [
    AppComponent,
    ArticleComponent,
    ArticleEditComponent,
  ],
  entryComponents: [ArticleEditComponent],
  imports: [
    BrowserModule, HttpClientModule, InfiniteScrollModule, NgxSpinnerModule, MatDialogModule, BrowserAnimationsModule, FormsModule
  ],
  providers: [AppComponent,
    {provide: HTTP_INTERCEPTORS, useClass: HttpApiPrefixInterceptor, multi: true},
    ArticleService
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
