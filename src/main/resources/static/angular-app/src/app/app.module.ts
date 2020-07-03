import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppComponent } from './app.component';
import { ArticleComponent } from './article/article.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {HttpApiPrefixInterceptor} from "./http-api-prefix.interceptor";

@NgModule({
  declarations: [
    AppComponent,
    ArticleComponent
  ],
  imports: [
    BrowserModule, HttpClientModule
  ],
  providers: [AppComponent,
    {provide: HTTP_INTERCEPTORS, useClass: HttpApiPrefixInterceptor, multi: true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
