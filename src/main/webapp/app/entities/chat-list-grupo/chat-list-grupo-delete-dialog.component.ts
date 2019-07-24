import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IChatListGrupo } from 'app/shared/model/chat-list-grupo.model';
import { ChatListGrupoService } from './chat-list-grupo.service';

@Component({
  selector: 'jhi-chat-list-grupo-delete-dialog',
  templateUrl: './chat-list-grupo-delete-dialog.component.html'
})
export class ChatListGrupoDeleteDialogComponent {
  chatListGrupo: IChatListGrupo;

  constructor(
    protected chatListGrupoService: ChatListGrupoService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  clear() {
    this.activeModal.dismiss('cancel');
  }

  confirmDelete(id: number) {
    this.chatListGrupoService.delete(id).subscribe(response => {
      this.eventManager.broadcast({
        name: 'chatListGrupoListModification',
        content: 'Deleted an chatListGrupo'
      });
      this.activeModal.dismiss(true);
    });
  }
}

@Component({
  selector: 'jhi-chat-list-grupo-delete-popup',
  template: ''
})
export class ChatListGrupoDeletePopupComponent implements OnInit, OnDestroy {
  protected ngbModalRef: NgbModalRef;

  constructor(protected activatedRoute: ActivatedRoute, protected router: Router, protected modalService: NgbModal) {}

  ngOnInit() {
    this.activatedRoute.data.subscribe(({ chatListGrupo }) => {
      setTimeout(() => {
        this.ngbModalRef = this.modalService.open(ChatListGrupoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
        this.ngbModalRef.componentInstance.chatListGrupo = chatListGrupo;
        this.ngbModalRef.result.then(
          result => {
            this.router.navigate(['/chat-list-grupo', { outlets: { popup: null } }]);
            this.ngbModalRef = null;
          },
          reason => {
            this.router.navigate(['/chat-list-grupo', { outlets: { popup: null } }]);
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
