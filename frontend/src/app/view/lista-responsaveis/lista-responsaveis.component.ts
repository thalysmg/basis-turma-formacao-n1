import { Component, OnInit } from '@angular/core';
import { Table } from 'primeng';
import { Responsavel } from '../../model/Responsavel';
import { Page } from '../../components/util/page';
import { ResponsavelService } from '../../services/responsavel.service';

@Component({
  selector: 'app-lista-responsaveis',
  templateUrl: './lista-responsaveis.component.html',
  styleUrls: ['./lista-responsaveis.component.css']
})
export class ListaResponsaveisComponent implements OnInit {

  dataTable: Table;

  responsaveis: Page<Responsavel>;

  responsavel: Responsavel = new Responsavel();
  
  constructor(private responsavelService: ResponsavelService) { }

  ngOnInit(): void {
    this.listarResponsaveis();
  }

  public listarResponsaveis() {
    console.log(this.responsavel)
    this.responsavelService.listar({ query: this.responsavel.nome }, this.dataTable).subscribe((response) => {
      this.responsaveis = response;
    });
  }
}
