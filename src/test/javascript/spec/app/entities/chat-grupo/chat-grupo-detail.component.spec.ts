/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp3TestModule } from '../../../test.module';
import { ChatGrupoDetailComponent } from 'app/entities/chat-grupo/chat-grupo-detail.component';
import { ChatGrupo } from 'app/shared/model/chat-grupo.model';

describe('Component Tests', () => {
  describe('ChatGrupo Management Detail Component', () => {
    let comp: ChatGrupoDetailComponent;
    let fixture: ComponentFixture<ChatGrupoDetailComponent>;
    const route = ({ data: of({ chatGrupo: new ChatGrupo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp3TestModule],
        declarations: [ChatGrupoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ChatGrupoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ChatGrupoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.chatGrupo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
