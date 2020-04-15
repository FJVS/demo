import {Component, OnInit} from '@angular/core';
import {OfficeService} from "../../services/office.service";

@Component({
  selector: 'app-new-office',
  templateUrl: './new-office.component.html',
  styleUrls: ['./new-office.component.scss']
})
export class NewOfficeComponent implements OnInit {

  id: any;
  name: any;
  type: any;
  image: any;


  constructor(private officeService: OfficeService) {
  }

  ngOnInit(): void {
  }

  bookItem() {

    const office: any = {
      id: null,
      name: this.name,
      type: this.type,
      image: this.image
    };
    this.officeService.addOffice(office).subscribe(() => {
      window.alert('Office added successfully.');
    }, error => {
      window.alert("Office addition was not successful.")
    });
  }

  onFileChanged(event) {

    const reader = new FileReader();
    reader.readAsDataURL(event.target.files[0]);
    console.log(event.target.files[0].size);
    reader.onload = (ev: any) => {
      this.image = (ev.target as FileReader).result.toString()
        .substr((ev.target as FileReader).result.toString().indexOf('base64,') + 'base64,'.length);
    };
    reader.onerror = (ev => {
      console.log(ev);
    });
  }

}
