import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {RatingService} from "../../services/rating.service";

@Component({
  selector: 'app-popup-rate',
  templateUrl: './popup-rate.component.html',
  styleUrls: ['./popup-rate.component.scss']
})
export class PopupRateComponent implements OnInit {

  grade: number;
  comments: string;
  @Input() bid: number;
  @Input() showPopupRate: boolean;
  @Output() closePopup: EventEmitter<any>;


  constructor(private ratingService: RatingService) {
    this.showPopupRate = false;
    this.closePopup = new EventEmitter<any>();
  }

  ngOnInit() {
  }

  onCancel() {
    this.showPopupRate = false;
    this.closePopup.emit(this.showPopupRate)
  }

  onSubmit() {
    const rating: any = {
      id: null,
      comment: this.comments,
      grade: this.grade,
      bid: this.bid,
      raterId: 33
    };
    this.ratingService.rateBooking(rating).subscribe(() => {
      window.alert('Rating submitted successfully.');
      this.showPopupRate = false;
      this.closePopup.emit(this.showPopupRate)
    }, error => {
      window.alert("Rating submission was not successful.")
      this.showPopupRate = false;
      this.closePopup.emit(this.showPopupRate)
    });
  }
}
