import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-dropdown',
  templateUrl: './dropdown.component.html',
  styleUrls: ['./dropdown.component.scss']
})
export class DropdownComponent implements OnInit {

  @Input() values: any[];
  @Input() showList: boolean;
  @Input() selectedValue: any;
  @Output() select: EventEmitter<any>;


  constructor() {
    this.select = new EventEmitter();
  }

  ngOnInit(): void {
  }

  toggleList() {
    this.showList = !this.showList;
  }

  selectItem(value: any) {
    this.showList = !this.showList;
    this.select.emit(value);
  }
}
