/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp3TestModule } from '../../../test.module';
import { ChatListGrupoUpdateComponent } from 'app/entities/chat-list-grupo/chat-list-grupo-update.component';
import { ChatListGrupoService } from 'app/entities/chat-list-grupo/chat-list-grupo.service';
import { ChatListGrupo } from 'app/shared/model/chat-list-grupo.model';

describe('Component Tests', () => {
  describe('ChatListGrupo Management Update Component', () => {
    let comp: ChatListGrupoUpdateComponent;
    let fixture: ComponentFixture<ChatListGrupoUpdateComponent>;
    let service: ChatListGrupoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp3TestModule],
        declarations: [ChatListGrupoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ChatListGrupoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ChatListGrupoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ChatListGrupoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ChatListGrupo(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new ChatListGrupo();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
