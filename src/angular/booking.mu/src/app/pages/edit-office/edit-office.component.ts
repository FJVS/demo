import {Component, OnInit} from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {OfficeService} from "../../services/office.service";

@Component({
  selector: 'app-edit-office',
  templateUrl: './edit-office.component.html',
  styleUrls: ['./edit-office.component.scss']
})

export class EditOfficeComponent implements OnInit {

  id: any;
  name: any;
  type: any;
  image: any;


  constructor(private route: ActivatedRoute, private officeService: OfficeService) {
  }

  ngOnInit(): void {
    this.route.paramMap.subscribe(params => {
      this.officeService.getOfficeById(+params.get('id')).subscribe((data) => {
        this.id = data.id;
        this.name = data.name;
        this.type = data.type;
      });
    });
  }

  bookItem() {

    const office: any = {
      id: this.id,
      name: this.name,
      type: this.type,
      image: this.image
    };
    this.officeService.updateOffice(office).subscribe(() => {
      window.alert('Office updated successfully.');
    }, error => {
      window.alert("Office update was not successful.")
    });
  }

  generatePhotoUrl(id: number) {
    return `http://localhost:8080/office/getImage/${id}`;
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
