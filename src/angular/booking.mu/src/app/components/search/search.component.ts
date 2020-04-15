import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent implements OnInit {

  @Input() searchText: any;
  @Output() textChanged: EventEmitter<any>;


  constructor() {
    this.textChanged = new EventEmitter<any>();
  }

  ngOnInit(): void {
  }

  change(text) {
    this.textChanged.emit(text);
  }
}
