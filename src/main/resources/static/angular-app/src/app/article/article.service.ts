import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Article} from "./article.model";

@Injectable()
export class ArticleService {

  constructor(private http: HttpClient) {}

  public getArticles(): Observable<Array<any>>  {
    return this.http.get<Array<any>>("/articles");
  }

  public getRandomArticles(limit: number): Observable<Array<any>>  {
    return this.http.get<Array<any>>(`/articles/random/${limit}`);
  }

  public getNextArticles(id: number, limit: number): Observable<Array<any>>  {
    return this.http.get<Array<any>>(`/articles/${id}/${limit}`);
  }
  public saveArticle(newArticle: Article): Observable<number>  {
    return this.http.put<number>(`/articles`, newArticle);
  }

  public modifyArticle(modifiedArticle: Article): Observable<number>  {
    return this.http.post<number>(`/articles`, modifiedArticle);
  }
}
