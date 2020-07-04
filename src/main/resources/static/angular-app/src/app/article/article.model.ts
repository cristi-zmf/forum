export class Article {
  id: number;
  title: string;
  link: string;
  votes: number;


  constructor(title: string, link: string, votes?: number) {
    this.title = title;
    this.link = link;
    this.votes = votes || 0;
  }
  public static fromArticleDto(article: any): Article {
    let articleModel = new Article(article.title, article.link, article.votes);
    articleModel.id = article.id;
    return articleModel;
  }

  makeMeHaveSameContentAs(article: Article) {
    this.title = article.title;
    this.link = article.link;
    this.votes = article.votes;
    this.id = article.id;
  }


  voteUp(): void {
    this.votes += 1;
  }

  voteDown(): void {
    this.votes -= 1;
  }

  domain(): string {
    try  {
      const domainAndPath: string = this.link.split('//')[1];
      return domainAndPath.split('/')[0];
    } catch (err) {
      return null;
    }
  }
}
