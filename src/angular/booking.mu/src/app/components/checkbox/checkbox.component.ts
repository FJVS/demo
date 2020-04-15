import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
  selector: 'app-checkbox',
  templateUrl: './checkbox.component.html',
  styleUrls: ['./checkbox.component.scss']
})
export class CheckboxComponent implements OnInit {

  @Input() checked: any[];
  @Input() values: any[];
  @Input() type: any;
  @Output() checkedChanged: EventEmitter<any>;

  constructor() {
    this.checkedChanged = new EventEmitter<any>();
  }

  ngOnInit() {
  }

  toggleChecked(id, type, name) {
    if (type == "equipments") {
      this.checked[id] = !this.checked[id];
      this.checkedChanged.emit({boolean: this.checked[id], id: id, type: type});
    } else {
      this.checked[id] = !this.checked[id];
      this.checkedChanged.emit({boolean: this.checked[id], name: name, id: id, type: type});
    }
  }
}
