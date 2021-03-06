import {Component, OnInit} from '@angular/core';
import {Article} from './article/article.model';
import {ArticleService} from "./article/article.service";
import {NgxSpinner} from "ngx-spinner/lib/ngx-spinner.enum";
import {NgxSpinnerService} from "ngx-spinner";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  articles: Article[];
  backupOfOriginalArticles: Article[];
  private searchKeyword: String = '';
  private isSearching: boolean = false;

  constructor(private articleService: ArticleService, private spinner: NgxSpinnerService) {
    this.articles = [
      // new Article('Angular Tour of Heroes', 'https://github.com/johnpapa/angular-tour-of-heroes', 3),
      // new Article('Angular for Beginners Guide',
      //   'https://blog.angular-university.io/getting-started-with-angular-setup-a-development-environment-with-' +
      //   'yarn-the-angular-cli-setup-an-ide/', 2),
      // new Article('Getting Started with Angular', 'https://www.ag-grid.com/angular-getting-started/', 1)
    ];

  }

  ngOnInit() {
    this.retrieveArticles();
  }

  private retrieveArticles() {
    this.spinner.show();
    this.retrieveNextBatchOfArticles();
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
    // this.articleService.saveArticle(newArticle);
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

    this.articles = this.searchForArticles(searchWord); //call backend function here

    console.log("Value %s", searchWord);
    console.log("Made backup of articles %o", this.backupOfOriginalArticles);
    if (searchWord === '' || !searchWord) {
      console.log("Exiting search mode");
      this.articles = [...this.backupOfOriginalArticles];
      this.isSearching = false;
      this.backupOfOriginalArticles = [];
    }
  }

  private searchForArticles(searchWord: string): Article[] {
    return this.articles.filter(a => a.title.toLowerCase().includes(searchWord.toLowerCase()));
  }
}
