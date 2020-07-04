import {Component, HostBinding, Input, OnInit} from '@angular/core';
import {Article} from './article.model';
import {MatDialog} from "@angular/material/dialog";
import {ArticleEditComponent} from "./edit/article-edit/article-edit.component";
import {RouterLink} from "@angular/router";

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.css']
})
export class ArticleComponent implements OnInit {
  @HostBinding('attr.class') cssClass = 'row';
  @Input() article: Article;

  constructor(private dialog: MatDialog) {}

  openDialog(): void {
    const dialogRef = this.dialog.open(ArticleEditComponent, {
      width: '500px',
      data: {article: this.article}
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
      // this.animal = result;
    });
  }



  votesUp() {
    this.article.voteUp();
    return false;
  }

  votesDown() {
    this.article.voteDown();
    return false;
  }

  ngOnInit() {
  }

}


