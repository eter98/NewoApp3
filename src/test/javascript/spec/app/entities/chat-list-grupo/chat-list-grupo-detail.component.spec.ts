/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { NewoApp3TestModule } from '../../../test.module';
import { ChatListGrupoDetailComponent } from 'app/entities/chat-list-grupo/chat-list-grupo-detail.component';
import { ChatListGrupo } from 'app/shared/model/chat-list-grupo.model';

describe('Component Tests', () => {
  describe('ChatListGrupo Management Detail Component', () => {
    let comp: ChatListGrupoDetailComponent;
    let fixture: ComponentFixture<ChatListGrupoDetailComponent>;
    const route = ({ data: of({ chatListGrupo: new ChatListGrupo(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [NewoApp3TestModule],
        declarations: [ChatListGrupoDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ChatListGrupoDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ChatListGrupoDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should call load all on init', () => {
        // GIVEN

        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.chatListGrupo).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
