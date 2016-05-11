import {AppComponent} from "../app/app.component";
import {componentTest} from "./support/controller-test";
import {describe, it} from "angular2/testing";

describe("AppComponent", () => {
  it("presents the title based on the underlying attribute", componentTest(AppComponent, (component, domEl, fixture) => {
    expect(domEl.innerText).toContain("Implement me!");

    component.title = "Not today.";
    fixture.detectChanges();

    expect(domEl.innerText).toContain("Not today.");
  }));
});