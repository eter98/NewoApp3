import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NewoApp3SharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective } from './';

@NgModule({
  imports: [NewoApp3SharedCommonModule],
  declarations: [JhiLoginModalComponent, HasAnyAuthorityDirective],
  entryComponents: [JhiLoginModalComponent],
  exports: [NewoApp3SharedCommonModule, JhiLoginModalComponent, HasAnyAuthorityDirective],
  schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NewoApp3SharedModule {
  static forRoot() {
    return {
      ngModule: NewoApp3SharedModule
    };
  }
}
