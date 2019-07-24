/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { NewoApp3TestModule } from '../../../test.module';
import { ChatListGrupoDeleteDialogComponent } from 'app/entities/chat-list-grupo/chat-list-grupo-delete-dialog.component';
import { ChatListGrupoService } from 'app/entities/chat-list-grupo/chat-list-grupo.service';

describe('Component Tests', () => {
  describe('ChatListGrupo Management Delete Component', () => {
    let comp: ChatListGrupoDeleteDialogComponent;
    let fixture: ComponentFixture<ChatListGrupoDeleteDialogComponent>;
    let service: ChatListGrupoService;
    let mockEventManager: any;
    let mockActiveModal: any;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp3TestModule],
        declarations: [ChatListGrupoDeleteDialogComponent]
      })
        .overrideTemplate(ChatListGrupoDeleteDialogComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ChatListGrupoDeleteDialogComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ChatListGrupoService);
      mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
      mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
    });

    describe('confirmDelete', () => {
      it('Should call delete service on confirmDelete', inject(
        [],
        fakeAsync(() => {
          // GIVEN
          spyOn(service, 'delete').and.returnValue(of({}));

          // WHEN
          comp.confirmDelete(123);
          tick();

          // THEN
          expect(service.delete).toHaveBeenCalledWith(123);
          expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
          expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
        })
      ));
    });
  });
});
