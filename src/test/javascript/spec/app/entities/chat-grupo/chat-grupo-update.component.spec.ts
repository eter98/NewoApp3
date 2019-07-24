/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { Observable, of } from 'rxjs';

import { NewoApp3TestModule } from '../../../test.module';
import { ChatGrupoUpdateComponent } from 'app/entities/chat-grupo/chat-grupo-update.component';
import { ChatGrupoService } from 'app/entities/chat-grupo/chat-grupo.service';
import { ChatGrupo } from 'app/shared/model/chat-grupo.model';

describe('Component Tests', () => {
  describe('ChatGrupo Management Update Component', () => {
    let comp: ChatGrupoUpdateComponent;
    let fixture: ComponentFixture<ChatGrupoUpdateComponent>;
    let service: ChatGrupoService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp3TestModule],
        declarations: [ChatGrupoUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ChatGrupoUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ChatGrupoUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ChatGrupoService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new ChatGrupo(123);
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
        const entity = new ChatGrupo();
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
