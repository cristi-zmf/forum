import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Article} from "../../article.model";
import {ArticleService} from "../../article.service";

@Component({
  selector: 'app-article-edit',
  templateUrl: './article-edit.component.html',
  styleUrls: ['./article-edit.component.css']
})
export class ArticleEditComponent implements OnInit {
  article: Article;
  constructor(public dialogRef: MatDialogRef<ArticleEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: ArticleData, private articleService: ArticleService) {

  }

  ngOnInit() {
    this.article = Article.fromArticleDto(this.data.article);
  }

  applyChanges() {
    this.data.article.makeMeHaveSameContentAs(this.article);
    // this.articleService.modifyArticle(this.article);
    this.dialogRef.close();
  }
}

export interface ArticleData {
  article: Article;
}
