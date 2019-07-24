import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChatGrupo } from 'app/shared/model/chat-grupo.model';
import { ChatGrupoService } from './chat-grupo.service';

@Component({
  selector: 'jhi-chat-grupo-delete-dialog',
  templateUrl: './chat-grupo-delete-dialog.component.html'
})
export class ChatGrupoDeleteDialogComponent {
  chatGrupo: IChatGrupo;

  constructor(protected chatGrupoService: ChatGrupoService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.chatGrupoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'chatGrupoListModification',
        content: 'Deleted an chatGrupo'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-chat-grupo-delete-popup',
  template: ''
})
export class ChatGrupoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ chatGrupo }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ChatGrupoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.chatGrupo = chatGrupo;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/chat-grupo', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/chat-grupo', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          }
        );
      }, 0);
    });
  }

  ngOnDestroy() {
    this.ngbModalRef = null;
  }
}
