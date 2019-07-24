import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IChatGrupo } from 'app/shared/model/chat-grupo.model';

@Component({
  selector: 'jhi-chat-grupo-detail',
  templateUrl: './chat-grupo-detail.component.html'
})
export class ChatGrupoDetailComponent implements OnInit {
  chatGrupo: IChatGrupo;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ chatGrupo }) => {
      this.chatGrupo = chatGrupo;
    });
  }

  previousState() {
    window.history.back();
  }
}
