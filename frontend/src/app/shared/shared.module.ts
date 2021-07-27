import { NgModule } from '@angular/core';
import { ResponsavelService } from '../services/responsavel.service';
import { PRIMENG_IMPORTS } from './primeng-imports';

@NgModule({
    imports: [
        PRIMENG_IMPORTS
    ],
    providers: [ ResponsavelService ],
    exports: [
        PRIMENG_IMPORTS,
    ]
})
export class SharedModule { }
