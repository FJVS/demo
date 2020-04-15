import {Component, OnInit} from '@angular/core';
import {OfficeService} from "../../services/office.service";

@Component({
  selector: 'app-current-offices',
  templateUrl: './current-offices.component.html',
  styleUrls: ['./current-offices.component.scss']
})
export class CurrentOfficesComponent implements OnInit {
  loaded: boolean;
  text: any;
  offices: any[];
  officesCopy: any[];
  showPopup: boolean;
  deleteId;
  type: string;

  constructor(private officeService: OfficeService) {
    this.loaded = false;
    this.type = "Office";
  }

  ngOnInit(): void {

    this.officeService.getOffices().subscribe((data) => {
        this.loaded = true;
        this.offices = data;
        this.officesCopy = data;
      }
      , error => (console.log(error))
    );
  }

  onTextChanged(value) {
    this.text = value;
    //search employee
    if (this.text == "") {
      this.offices = this.officesCopy;
    } else {
      this.officeService.getOfficeByName(this.text).subscribe((data) => {
        this.loaded = true;
        this.offices = data;
      });
    }
  }

  showDeletePopup(id) {
    this.deleteId = id;
    this.showPopup = true;
  }

  closePopup(value: boolean) {
    this.showPopup = value;
  }
}
