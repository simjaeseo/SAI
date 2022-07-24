<template>
  <div>
    <div class="container">
      <h1>April</h1>
      <div class="calender cal-view" data-component="calender-module">
        <a href="#" class="toggle">toggle view</a>
        <div class='calender__month'>
          <div class='calender__week'>
            <div class='calender__day--hidden'>
              <span class="date">29</span>
            </div>
            <div class='calender__day--hidden'>
              <span class="date">30</span>
            </div>
            <div class='calender__day--hidden'>
              <span class="date">31</span>
            </div>
            <div class='calender__day'>
              <span class="date">01</span>
            </div>
            <div class='calender__day--empty'>
              <span class="date">02</span>
            </div>
            <div class='calender__day--empty'>
              <span class="date">03</span>
            </div>
            <div class='calender__day'>
              <span class="date">04</span>
            </div>
          </div>
          <div class='calender__week'>
            <div class='calender__day--empty'>
              <span class="date">05</span>
            </div>
            <div class='calender__day'>
              <span class="date">06</span>
            </div>
            <div class='calender__day'>
              <span class="date">07</span>
            </div>
            <div class='calender__day'>
              <span class="date">08</span>
            </div>
            <div class='calender__day'>
              <span class="date">09</span>
            </div>
            <div class='calender__day--empty'>
              <span class="date">10</span>
            </div>
            <div class='calender__day--empty'>
              <span class="date">11</span>
            </div>
          </div>
          <div class='calender__week'>
            <div class='calender__day'>
              <span class="date">12</span>
            </div>
            <div class='calender__day--empty'>
              <span class="date">13</span>
            </div>
            <div class='calender__day'>
              <span class="date">14</span>
            </div>
            <div class='calender__day'>
              <span class="date">15</span>
            </div>
            <div class='calender__day'>
              <span class="date">16</span>
            </div>
            <div class='calender__day--empty'>
              <span class="date">17</span>
            </div>
            <div class='calender__day'>
              <span class="date">18</span>
            </div>
          </div>
          <div class='calender__week'>
            <div class='calender__day'>
              <span class="date">19</span>
            </div>
            <div class='calender__day'>
              <span class="date">20</span>
            </div>
            <div class='calender__day'>
              <span class="date">21</span>
            </div>
            <div class='calender__day'>
              <span class="date">22</span>
            </div>
            <div class='calender__day'>
              <span class="date">23</span>
            </div>
            <div class='calender__day--empty'>
              <span class="date">24</span>
            </div>
            <div class='calender__day--empty'>
              <span class="date">25</span>
            </div>
          </div>
          <div class='calender__week'>
            <div class='calender__day'>
              <span class="date">26</span>
            </div>
            <div class='calender__day'>
              <span class="date">27</span>
            </div>
            <div class='calender__day--empty'>
              <span class="date">28</span>
            </div>
            <div class='calender__day'>
              <span class="date">29</span>
              <div class="event-list">
                <div class="event-list__event">
                  <a href="#">2 Unlimited: Reach for the sky</a>
                </div>
              </div>
            </div>
            <div class='calender__day--empty'>
              <span class="date">30</span>
            </div>
            <div class='calender__day--empty'>
              <span class="date">31</span>
            </div>
            <div class='calender__day--hidden'>
              <span class="date">1</span>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'MyCalendar',
  mounted() {
    const toggle = document.querySelector('.toggle');
    const calender = document.querySelector('.calender');

    const toggleClass = (el, className) => {
      if (el.classList) {
        el.classList.toggle(className);
      } else {
        const classes = el.className.split(' ');
        const existingIndex = classes.indexOf(className);

        if (existingIndex >= 0) {
          classes.splice(existingIndex, 1);
        } else {
          classes.push(className);
        }

        const tmp = el;
        tmp.className = classes.join(' ');
      }
    };

    toggle.addEventListener('click', (e) => {
      console.log(e);
      toggleClass(calender, 'cal-view');
    });
  },
};
</script>

<style scoped>

template {
  box-sizing: border-box;
  background: #000000;
  color: #ffffff;
}

/**
   * calender
   *
   */
.calender {
  max-width: 62.5em;
  margin: auto;
}
.calender__month {
  background: #2f2f2f;
  height: 100%;
}

.calender__day, .calender__day--empty, .calender__day--hidden {
  background: rgba(255, 255, 255, 0.1);
  flex: 1 1 0;
  /* padding: 0.625em; */
  border: 0.0625em solid rgba(255, 255, 255, 0.2);
  /* margin: 0.125em; */
  height: 6em;
  /* min-height: 5.75em;
  max-height: 7em; */
}
.calender__day--hidden {
  opacity: 0.3;
  display: none;
}

.calender__day--empty {
  display: none;
}

.calender__day .date, .calender__day--hidden .date, .calender__day--empty .date {
  color: #ffffff;
  display: flex;
  padding: 0.25em 0em 0em 0.25em;
  justify-content: start;
}

@media screen and (min-width: 48em) {
  .calender.cal-view .calender__week {
    display: flex;
  }
}
.calender.cal-view .calender__day--hidden,
.calender.cal-view .calender__day--empty {
  display: none;
}
@media screen and (min-width: 48em) {
  .calender.cal-view .calender__day--hidden,
.calender.cal-view .calender__day--empty {
    display: block;
  }
}
.calender .toggle {
  display: none;
  color: #ffffff;
  text-decoration: none;
  margin-bottom: 0.625em;
  text-align: right;
}
@media screen and (min-width: 48em) {
  .calender .toggle {
    display: block;
  }
}

/**
   * event-list
   *
   */
.event-list__event a {
  font-size: 0.75em;
  color: #ffffff;
  text-decoration: none;
}
.event-list__event + .event-list__event {
  margin-top: 0.625em;
}
</style>
