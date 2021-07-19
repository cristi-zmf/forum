import {Component, OnInit} from '@angular/core';
import {Article} from './article/article.model';
import {ArticleService} from "./article/article.service";
import {NgxSpinner} from "ngx-spinner/lib/ngx-spinner.enum";
import {NgxSpinnerService} from "ngx-spinner";
import {ArticleSearchCriteria} from "./article/article-search-criteria";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  articles: Article[] = [];
  backupOfOriginalArticles: Article[];
  private searchKeyword: String = '';
  private isSearching: boolean = false;

  constructor(private articleService: ArticleService, private spinner: NgxSpinnerService) {
  }

  ngOnInit() {
    this.retrieveArticles();
  }

  private retrieveArticles() {
    this.spinner.show();
    this.retrieveNextBatchOfArticles()
  }

  private retrieveRandomBatchOfArticles() {
    this.articleService.getRandomArticles(10).subscribe((data: Array<any>) => {
        data.forEach(e => this.articles.push(Article.fromArticleDto(e)));
        this.spinner.hide();
      }
    );
  }

  private retrieveNextBatchOfArticles() {
    let lastRetrievedId = 0;
    if (this.articles.length > 0) {
      let lastArticle: Article = this.articles[this.articles.length - 1];
      lastRetrievedId = lastArticle.id;
    }
    this.articleService.getNextArticles(lastRetrievedId, 10).subscribe((data: Array<any>) => {
        this.pushNewArticlesToUIListOfArticles(data);
        this.spinner.hide();
      }
    );
  }

  private retrieveAllArticles() {
    this.articleService.getArticles().subscribe((data: Array<any>) => {
        this.pushNewArticlesToUIListOfArticles(data);
        this.spinner.hide();
      }
    );
  }

  addArticle(title: HTMLInputElement, link: HTMLInputElement): boolean {
    console.log(`Adding article title: ${title.value} and link: ${link.value}`);
    let newArticle = new Article(title.value, link.value, 0);
    this.articles.push(newArticle);
    this.articleService.saveArticle(newArticle);
    title.value = '';
    link.value = '';
    return false;
  }

  private pushNewArticlesToUIListOfArticles(data: Array<any>) {
    data.forEach(e => this.articles.push(Article.fromArticleDto(e)));
  }

  sortedArticles(): Article[] {
    return this.articles.sort((a: Article, b: Article) => b.votes - a.votes);
  }

  onScroll() {
    console.log("Scrolled")
    this.retrieveArticles();
  }

  searchForKey($event: KeyboardEvent) {
    let searchWord = (event.target as HTMLInputElement).value;


    if (this.isSearching === false) {
      this.backupOfOriginalArticles = [...this.articles]
      this.isSearching = true;
    }

    // this.articles = this.searchForArticles(searchWord); //call backend function here
    this.searchForArticles(searchWord); //call backend function here

    console.log("Value %s", searchWord);
    console.log("Made backup of articles %o", this.backupOfOriginalArticles);
    if (searchWord === '' || !searchWord) {
      console.log("Exiting search mode");
      this.articles = [...this.backupOfOriginalArticles];
      this.isSearching = false;
      this.backupOfOriginalArticles = [];
    }
  }

  private searchForArticles(searchWord: string): void {
    // return this.articles.filter(a => a.title.toLowerCase().includes(searchWord.toLowerCase()));
    let searchCriteria: ArticleSearchCriteria = {
      articleName: searchWord
    };
    this.articleService.searchArticle(searchCriteria).subscribe(
      (data: Article[]) => this.articles = data.map(a => Article.fromArticleDto(a))
    );
  }
  // private searchForArticles(searchWord: string): Article[] {
  //   // return this.articles.filter(a => a.title.toLowerCase().includes(searchWord.toLowerCase()));
  //   let searchCriteria: ArticleSearchCriteria = {
  //     articleName : searchWord
  //   };
  //   this.articleService.searchArticle(searchCriteria).subscribe((data: Article[]) => this.articles = [...data]);
  //   return this.articles
  // }
}
