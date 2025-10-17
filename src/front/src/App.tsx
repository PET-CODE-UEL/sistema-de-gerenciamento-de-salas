import {
  Card,
  CardDescription,
  CardHeader,
  CardTitle,
} from "@/components/ui/card";
import "./index.css";

import logo from "../public/logo_color.png";

export function App() {
  return (
    <div className="container mx-auto p-8 text-center relative z-10 flex flex-col w-lg">
      <div className="flex justify-center items-center gap-8 mb-8">
        <img
          src={logo}
          alt="UEL Logo"
          className="h-36 p-6 transition-all duration-300 hover:drop-shadow-[0_0_2em_#646cffaa] scale-120"
        />
      </div>
      <Card className="w-[10rem] flex grow size-full">
        <CardHeader className="gap-4">
          <CardTitle className="text-3xl font-bold">Uniroom</CardTitle>
          <CardDescription>
            A{" "}
            <a
              href="https://www.instagram.com/petcode_uel/"
              className="px-[0.3rem] py-[0.2rem] font-bold hover:opacity-80 transition-opacity duration-300"
            >
              PET Code
            </a>{" "}
            project
          </CardDescription>
        </CardHeader>
      </Card>
    </div>
  );
}

export default App;
